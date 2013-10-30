__author__ = 'muneeb'


class DFS():
    __start = None
    __goal = None
    __graph = None
    __goal_found = None
    __nodes_expanded = None
    __max_no_of_elements = None
    __no_of_elements = None


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
        print "\nRunning DFS"
        self.__goal_found = False
        self.__walk(self.__start, 0)
        print "Nodes expanded: ", self.__nodes_expanded, " Elements in memory: ", self.__max_no_of_elements

    def set_max_no_of_elements(self, no):
        if no > self.__max_no_of_elements:
            self.__max_no_of_elements = no

    def __walk(self, node, level):
        if self.__goal_found:
            return
        self.set_max_no_of_elements(level)
        self.__graph[node].set_is_explored(True)
        self.__nodes_expanded += 1
        self.__check_goal_found(node)
        for adjacent_node in self.__graph[node].get_adjacent_nodes():
            if self.__graph[adjacent_node].get_is_explored():
                continue
            self.__walk(adjacent_node, level + 1)