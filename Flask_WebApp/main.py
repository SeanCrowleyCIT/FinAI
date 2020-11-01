from flask import Flask, request, redirect, url_for
from formsflask import MyForm
from flask import render_template
from Wiki import wiki, list_pick
app = Flask(__name__)
import os
SECRET_KEY = os.urandom(32)
app.config['SECRET_KEY'] = SECRET_KEY


@app.route('/', methods=['GET','POST'])
def search():
    form = MyForm()
    if form.validate_on_submit():
        print("form validated!")
        data = form.username.data
    else:
        print("form did not validate")
    return render_template("index.html", title='Search', form=form)


@app.route('/pick_list', methods=['GET','POST'])
def pick_list():
    form = MyForm()
    search_term = request.args.get('data')
    url = wiki(search_term)
    numbered_list = []
    count = 0
    #for item in url:
       # count +=1
        #numbered_list[count-1] = url[count-1]+ str(count)
        #print(numbered_list[count-1])
    if form.validate_on_submit():

        print("form validated!")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        print (search_term)
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        data = list_pick(url, form.username.data)
        return redirect(url_for('wiki_list', data = data))
    else:
        print("form did not validate")
    return render_template("list_pick_form.html", data = url, title='Search', form=form)


@app.route('/wiki_list', methods=['GET','POST'])
def wiki_list():
    return render_template("list.html", data = request.args.get('data'))

'''
@app.route('/', methods=('GET', 'POST'))
def submit():
    form = MyForm()
    if form.validate_on_submit():
        return redirect('/')
    return render_template('login.html', form=form)

'''
if __name__== "__main__":
    app.debug = True
    app.run()    
    
