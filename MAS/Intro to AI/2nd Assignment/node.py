__author__ = 'muneeb'


class Node(object):
    is_explored = False
    __adjacent_nodes = None
    __key = None
    __data = None

    def __init__(self, key, data, adjacent_nodes):
        self.__key = key
        self.__data = data
        self.__adjacent_nodes = adjacent_nodes

    def get_node_key(self):
        return self.__key

    def get_adjacent_nodes(self):
        return self.__adjacent_nodes

    def get_data(self):
        return self.__data