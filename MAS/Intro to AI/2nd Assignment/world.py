__author__ = 'muneeb'

from node import Node


class World():
    __start_rep = "s"
    __dirt_rep = "*"
    __world = None
    __start = None
    __vertical_boundary = "|"
    __horizontal_boundary = "="
    __new_line = "\n"

    def dirt(self):
        return self.__dirt_rep

    def __is_boundary(self, char):
        return char == self.__horizontal_boundary or char == self.__vertical_boundary or char == self.__new_line

    def __init__(self, world):
        self.__world = {}
        """:type : dictionary"""
        for x, row in enumerate(world):
            for y, data in enumerate(row):
                if not self.__is_boundary(data):
                    node_key = self.__get_node_position(x, y)
                    self.__world[node_key] = Node(node_key, data, self.__find_adjacent_nodes(world, x, y))
                    if data == "s":
                        self.__start = node_key

    def get_start(self):
        return self.__start

    def __get_node_position(self, x, y):
        return str(x) + "," + str(y)

    def __find_adjacent_nodes(self, world, row, column):
        """
        :type world: list
        """
        adjacent_nodes = []
        left_node = world[row][column - 1]
        right_node = world[row][column + 1]
        up_node = world[row - 1][column]
        down_node = world[row + 1][column]
        if not self.__is_boundary(left_node):
            adjacent_nodes.append(self.__get_node_position(row, column - 1))
        if not self.__is_boundary(right_node):
            adjacent_nodes.append(self.__get_node_position(row, column + 1))
        if not self.__is_boundary(up_node):
            adjacent_nodes.append(self.__get_node_position(row - 1, column))
        if not self.__is_boundary(down_node):
            adjacent_nodes.append(self.__get_node_position(row + 1, column))
        return adjacent_nodes

    def get_is_explored(self, node_key):
        return self.__world[node_key].is_explored

    def set_is_explored(self, node_key, explored):
        self.__world[node_key].is_explored = explored

    def get_adjacent_nodes(self, node_key):
        return self.__world[node_key].get_adjacent_nodes()

    def get_node_data(self, node_key):
        return self.__world[node_key].get_data()