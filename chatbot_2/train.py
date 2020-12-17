# import ChatBot from python chatterbot library
from chatterbot import ChatBot

# create a new instance of the ChatBot class - a new chat bot called FinAIBot
chatbot = ChatBot('FinAIBot',
    # SQLStorageAdapter allows the chat bot to connect to SQL databases
	# by default, this adapter will create a SQLite database
    storage_adapter='chatterbot.storage.SQLStorageAdapter',
	# logic adapters determine the logic for how ChatBot selects a response to a given input statement
    logic_adapters=[
		# the MathematicalEvaluation adapter solves math problems that use basic operations
        'chatterbot.logic.MathematicalEvaluation',
		# the TimeLogicAdapter returns the current time when the input statement asks for it
        'chatterbot.logic.TimeLogicAdapter',
		# a logic adapter that returns a response based on known responses to the closest matches to the input statement
        'chatterbot.logic.BestMatch',
        {
            'import_path': 'chatterbot.logic.BestMatch',
			# the maximum amount of similarity between two statements that is required before the search process is halted
			# the search for a matching statement will continue until a statement with a greater than or equal similarity is found
			# or the search set is exhausted
            'maximum_similarity_threshold': 0.90
			'default_response': 'I am sorry, but I do not understand.',

        }
    ],
	# the database parameter is used to specify the path to the database that the chat bot will use
	# here we will call the database database.sqlite3
	# this file will be created automatically if it doesn’t already exist
    database_uri='sqlite:///database.sqlite3'
)

# ListTrainer allows the chat bot to be trained using a list of strings where the list represents a conversation
from chatterbot.trainers import ListTrainer
trainer = ListTrainer(chatbot)
# train the chat bot with own questions and answers from the questions.txt file
conversations = open('data/questions.txt','r').readlines()
trainer.train(conversations)
