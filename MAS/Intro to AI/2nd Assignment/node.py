__author__ = 'muneeb'


class Node(object):
    __x_coordinate = None
    __y_coordinate = None
    __key = None
    __data = None
    __adjacent_nodes = None
    __is_explored = False

    def __init__(self, adjacent_nodes):
        self.__adjacent_nodes = adjacent_nodes

    def __init__(self, x, y, data):
        self.__x_coordinate = x
        self.__y_coordinate = y
        self.__data = data
        self.__key = str(self.x_coordinate()) + "," + str(self.y_coordinate())

    def x_coordinate(self):
        return self.__x_coordinate

    def y_coordinate(self):
        return self.__y_coordinate

    def get_node_key(self):
        return self.__key

    def get_data(self):
        return self.__data

    def set_is_explored(self, explored):
        self.__is_explored = explored

    def get_is_explored(self):
        return self.__is_explored

    def get_adjacent_nodes(self):
        return self.__adjacent_nodes