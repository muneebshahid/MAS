__author__ = 'muneeb'


class Node(object):
    __x_coordinate = None
    __y_coordinate = None
    __key = None
    __data = None

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