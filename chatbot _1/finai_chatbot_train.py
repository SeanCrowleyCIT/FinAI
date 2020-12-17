#the module NLTK (Natural Language Toolkit) is used for natural language processing
import nltk
#In many languages, words appear in several inflected forms. For example, in English, the verb 'to walk' 
#may appear as 'walk', 'walked', 'walks' or 'walking'. The base form, 'walk', that one might look up in 
#a dictionary, is called the lemma for the word. Lemmatization attempts to select the correct lemma depending
#on the context.
from nltk.stem import WordNetLemmatizer
lemmatizer = WordNetLemmatizer()
#JSON (JavaScript Object Notation) is a lightweight open standard data-interchange file format, that uses 
#human readable text for transmitting data. Although you may conclude from the name that it's a Javascript 
#data format. Well, not exactly, JSON is a text format that is completely language independent and uses 
#conventions that are familiar of most popular programming languages such as Python.
import json
#Pickling is used to store python objects. It is the process of converting a Python object (lists, dictionaries, tuples, etc) 
#into byte streams that can be saved to disks or can be transferred over a network. In de-serialization or unpickling the byte 
#streams saved on file contains the necessary information to reconstruct the original python object. 
import pickle
#NumPy is a library for the Python programming language, adding support for large, multi-dimensional arrays and matrices, 
#along with a large collection of high-level mathematical functions to operate on these arrays.
import numpy as np
#Keras is an open-source library that provides a Python interface for artificial neural networks. Keras acts as an 
#interface for the TensorFlow library. It is used to create a deep learning model for both regression and classification problems.

from keras.models import Sequential
from keras.layers import Dense, Activation, Dropout
from keras.optimizers import SGD
import random

words=[]
classes = []
documents = []
ignore_words = ['?', '!']
data_file = open('intents.json').read()
intents = json.loads(data_file)


for intent in intents['intents']:
    for pattern in intent['patterns']:

        #the sentences are split into words using the method word_tokenize()
        w = nltk.word_tokenize(pattern)
        words.extend(w)
        #add documents in the corpus
        documents.append((w, intent['tag']))

        # add to our classes list
        if intent['tag'] not in classes:
            classes.append(intent['tag'])

#Here we lemmaztize and lower each word and remove duplicates.
words = [lemmatizer.lemmatize(w.lower()) for w in words if w not in ignore_words]
words = sorted(list(set(words)))
# sort classes
classes = sorted(list(set(classes)))
# documents = combination between patterns and intents
print (len(documents), "documents")
# classes = intents
print (len(classes), "classes", classes)
# words = all words, vocabulary
print (len(words), "unique lemmatized words", words)

#The steps for pickling in python:
#1. Import pickle module.
#2. Use pickle.dump(object, filename) method to save the object into file <filename>: this will save the object in this file in byte format.
#3. Use pickle.load(filename): to load back python object from the file where it was dumped before.
#Here the files are opened in write-bytes “wb” mode
pickle.dump(words,open('words.pkl','wb'))
pickle.dump(classes,open('classes.pkl','wb'))

# create our training data
training = []
# create an empty array for our output
output_empty = [0] * len(classes)
# training set, bag of words for each sentence
for doc in documents:
    # initialize our bag of words
    bag = []
    # list of tokenized words for the pattern
    pattern_words = doc[0]
    # lemmatize each word - create base word, in attempt to represent related words
    pattern_words = [lemmatizer.lemmatize(word.lower()) for word in pattern_words]
    # create our bag of words array with 1, if word match found in current pattern
    for w in words:
        bag.append(1) if w in pattern_words else bag.append(0)
    
    # output is a '0' for each tag and '1' for current tag (for each pattern)
    output_row = list(output_empty)
    output_row[classes.index(doc[1])] = 1
    
    training.append([bag, output_row])
# shuffle our features and turn into np.array
random.shuffle(training)
training = np.array(training)
# create train and test lists. X - patterns, Y - intents
train_x = list(training[:,0])
train_y = list(training[:,1])
print("Training data created")


#Instantiate the keras model. The sequential API is the easiest model to build and run in Keras. A sequential model allows us 
#to create models layer by layer in a step by step fashion. The main task of the model is to arrange the layers of the Keras in 
#sequential order. In this model, the data flows from one layer to another layer. The flow of data is continued until the data reaches the final layer.
model = Sequential()
#Create a model with 3 layers 
#Layer 1: Input layer with 128 neurons
model.add(Dense(128, input_shape=(len(train_x[0]),), activation='relu'))
#Dropout layers are commonly used to prevent overfitting
model.add(Dropout(0.5))
#Layer 2: Hidden layer with 64 neurons
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.5))
#Layer 3: Output layer contains number of neurons equal to number of intents to predict output intent with softmax (an 
#activation function that represents multiple outcomes for a classification problem)
model.add(Dense(len(train_y[0]), activation='softmax'))

# Compile model
#Stochastic gradient descent with Nesterov accelerated gradient gives good results for this model
sgd = SGD(lr=0.01, decay=1e-6, momentum=0.9, nesterov=True)
model.compile(loss='categorical_crossentropy', optimizer=sgd, metrics=['accuracy'])

#to train the model we need to call its fit() method
hist = model.fit(np.array(train_x), np.array(train_y), epochs=200, batch_size=5, verbose=1)
#saving the model
model.save('chatbot_model.h5', hist)

print("model created")
