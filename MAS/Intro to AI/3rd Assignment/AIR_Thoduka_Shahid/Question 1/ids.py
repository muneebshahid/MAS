__author__ = 'muneeb'
import sys
from node import Node
import copy

class IDS():
    __depth = None
    __start = None
    __goal = None
    __graph = None
    __goal_found = None
    __max_no_of_elements = None

    def __init__(self, graph, start, goal):
        self.__graph = graph
        self.__start = start
        self.__goal = goal
        self.__goal_found = False
        self.__nodes_expanded = 0

    def __check_goal_found(self, node):
        if self.__goal == node:
            self.__goal_found = True


    def walk(self):
        print "\nRunning IDS"
        goal_found = False
        self.__depth = 0
        self.__max_no_of_elements = 1
        while not self.__goal_found:
            self.__depth += 1
            self.__walk(copy.deepcopy(self.__graph), self.__start, 1)
        print "Nodes expanded: ", self.__nodes_expanded, " Elements in memory: ", self.__max_no_of_elements

    def set_max_no_of_elements(self, no):
        if no > self.__max_no_of_elements:
            self.__max_no_of_elements = no

    def __walk(self, graph, node, level):
        if level == self.__depth or self.__goal_found:
            return
        self.set_max_no_of_elements(level)
        graph[node].set_is_explored(True)
        self.__nodes_expanded += 1
        self.__check_goal_found(node)
        for adjacent_node in graph[node].get_adjacent_nodes():
            if graph[adjacent_node].get_is_explored():
                continue
            self.__walk(graph, adjacent_node, level + 1)
