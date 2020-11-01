#import wikipedia
import requests
from bs4 import BeautifulSoup
def wiki (pageName):
    google_list = []
    count = 0
    try: 
        from googlesearch import search 
    except ImportError:  
        print("No module named 'google' found") 
  
# to search 
  
    for j in search(pageName + " wiki", tld="co.in", num=10, stop=10, pause=2):
        count+=1
        print(count,". ",j)
        google_list.append(j)
    return google_list


def list_pick(google_list, num):
    #x = input("pick number from returned list of urls: ")
    page = requests.get(google_list[(int(num) -1)])
    print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    print(page)
    print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    soup = BeautifulSoup(page.content, 'html.parser')
    whitelist = [
        'p',
        'b',
        'i',
        'h2',
        'h3',
        'a'
    ]
    blacklist = [
  'style',
  'script',
  'tr'
    ]   
    path = soup.find('div',attrs={'class':'mw-parser-output'})
    text_elements = [t for t in path.find_all(text=True) if t.parent.name in whitelist and t.parent.name not in blacklist]
    #title = soup.find(id="mw-content-text")
    wiki_text = str(text_elements).replace("u'", '')
    #wiki_text = str(wiki_text).replace('[', '')
    #wiki_text = str(wiki_text).replace('\n', '')
    #wiki_text = wiki_text.replace("\n',", "'")
    return wiki_text
    #print(wiki_text)
    #contents = page.content
    #print (contents)
    #print (pageName)
    #result = wikipedia.page() 
    #print(result.content) 
