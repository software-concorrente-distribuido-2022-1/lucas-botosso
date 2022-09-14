import threading
import time
import random

class Tela:
    texto = ''

    def setTexto(self, t):
        self.texto = t

    def mostraTexto(self):
        print(threading.current_thread().name+' imprimindo: '+self.texto)

class UserSemControle(threading.Thread):
    
    def __init__(self, nomeThread, recurso):
        threading.Thread.__init__(self)
        self.nomeThread = nomeThread
        self.recurso = recurso

    def run(self):

        for i in range(5):
            self.recurso.setTexto(self.nomeThread)
            time.sleep(0.03)
            self.recurso.mostraTexto()

recurso = Tela()

qtdThreads = 5
threads = []

#criar threads
for i in range(qtdThreads):
    t = UserSemControle(('Usuário '+str(i+1)), recurso)
    threads.append(t)

random.shuffle(threads)


for t in threads:
    t.start()