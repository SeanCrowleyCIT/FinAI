from flask import Flask, jsonify, request, redirect, url_for, render_template
from flask_restful import Resource, Api, reqparse
app = Flask(__name__)
api = Api(app)
from formsflask import MyForm
import os
pyrelist = []
SECRET_KEY = os.urandom(32)
app.config['SECRET_KEY'] = SECRET_KEY

import pyrebase
config = {
  "apiKey": "AIzaSyBnMEwJ4cfyivrofIUb3EEC2qmmCU7oTHo",
  "authDomain": "fin-ai-50427.firebaseapp.com",
  "databaseURL": "https://fin-ai-50427.firebaseio.com",
  "storageBucket": "fin-ai-50427.appspot.com",
  "serviceAccount": "fin-ai-50427-firebase-adminsdk-vj6dm-c630798cad.json"
}
firebase = pyrebase.initialize_app(config)
db = firebase.database()



@app.route('/login', methods=['GET','POST'])
def search():
    form = MyForm()
    if form.validate_on_submit():
        print("form validated!")
	print(form.email.data)
	print(form.password.data)
	Email = form.email.data
	Password = form.password.data
	newUser = {"Email": Email, "Password": Password}
	db.child("users").push(newUser)
	return redirect("/showDb")
       # data = request.form['username']
    else:
        print("form did not validate")
    return render_template("login.html", title='login', form=form)

@app.route('/showDb', methods=['GET','POST'])
def showDb():
	
	all_users = db.child("users").get()
	for Values in all_users.each():
		print(Values)
		pyrelist.append(Values.val())
	return jsonify(pyrelist)




reqparse = reqparse.RequestParser()
reqparse.add_argument('username', type = str)
reqparse.add_argument('password', type = str)


class newUserAPI(Resource):
        def post(self):
		args = reqparse.parse_args()
		username = args['username']
		password = args['password']
		print (username)
		print (password)
        	newUser = {"Email": username, "Password": password}
		db.child("users").push(newUser)
		return "information added to database"
 	def get(self):
		
		args = reqparse.parse_args()
		Email = args['username']
		password = args['password']
        	all_users = db.child("users").get()
		for Values in all_users.each():
			if (Values.val()["Email"]==Email and Values.val()["Password"]==password):
				return "login succesful"
		

		return "login incorrect"

api.add_resource(newUserAPI, '/newUser')



if __name__ == '__main__':
    debug=True
    app.run()
