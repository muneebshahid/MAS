__author__ = 'muneeb'
import sys
from node import Node

class IDS():
    __depth = None
    __goal = None
    __graph = None

    current_label = None
    __graph = None
    current_label = None
    def __init__(self):
        self.__goal = "g"
        self.__graph = {}
        self.__graph["s"] = Node(["a","b"])
        self.__graph["a"] = Node(["s","b","c"])
        self.__graph["b"] = Node(["s","a","d"])
        self.__graph["c"] = Node(["a","d","e"])
        self.__graph["d"] = Node(["b","c","e"])
        self.__graph["e"] = Node(["c", "d", self.__goal])
        self.__graph[self.__goal] = Node([""])

    def walk(self):
        while (True):
            self.walk("s", 0)

    def __walk(self, head, level):
        if level == self.__depth:
            return
        self.__graph[head].is_explored = True
        print(head)
        if (head == self.__goal):
            exit()
        for adjacent_node in self.__graph[head].adjacent_nodes:
            if(self.__graph[adjacent_node].is_explored): continue
            self.walk(adjacent_node, level + 1)


ids = IDS()
ids.walk()