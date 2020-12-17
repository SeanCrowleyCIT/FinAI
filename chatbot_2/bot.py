# A module is a file containing Python definitions and statements. The file name is the module name with the suffix .py appended.
# here we import the chatbot created in the train.py file/module
from train import chatbot
from flask import Flask, render_template, request

# run the application as a single module to initialize a new flask instance with the argument __name__
app = Flask(__name__)
# this will help flask to find the html files folder (containing html files) in this case static which is in the same folder
app.static_folder = 'static'

# modern web apps use a technique named routing. This helps the user remember the URLs. For instance, instead of having /booking.php they see
# /booking/. Instead of /account.asp?id=1234/ they’d see /account/1234/. Routes in Flask are mapped to Python functions: @app.route("/") is 
# a Python decorator that Flask provides to assign URLs in our app to functions easily. It's easy to understand what is happening at first 
# glance: the decorator is telling our @app that whenever a user visits our app domain at the given .route(), execute the home() function
@app.route("/")
def home():
	# the render_template function will look in the "templates" folder for a file called "index.html" and render it to the screen
    return render_template("index.html")

# Flask has different decorators to handle http requests. Different methods for retrieving data from a specified URL are defined in this protocol.
# the http method GET the most common method. A GET message is send, and the server returns data
@app.route("/get")
def get_bot_response():
	# request.args is gets a "dictionary" object for you. The "dictionary" object is similar to other collection-type of objects in Python, in that it 
	# can store many elements in one single object. It takes one parameter, one object, a "dictionary" type of object. This "dictionary" object, however, can
	# have as many elements as needed.
    userText = request.args.get('msg')
    return str(chatbot.get_response(userText))

# the code within the if block will be executed only when the code runs directly and is not imported
if __name__ == "__main__":
    app.run()