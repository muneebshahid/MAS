__author__ = 'muneeb'


class Node(object):
    __adjacent_nodes = None
    __is_explored = None

    def __init__(self, adjacent_nodes):
        self.__adjacent_nodes = adjacent_nodes
        self.__is_explored = False

    def set_is_explored(self, explored):
        self.__is_explored = explored

    def get_is_explored(self):
        return self.__is_explored

    def get_adjacent_nodes(self):
        return self.__adjacent_nodes