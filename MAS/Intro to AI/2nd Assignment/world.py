__author__ = 'muneeb'

from node import Node
from util import Util

class World():
    __start_rep = "s"
    __dirt_rep = "*"
    __vertical_boundary = "|"
    __horizontal_boundary = "="
    __new_line = "\n"
    __world = None
    __path = None
    __explored = None
    __fringe = None
    __start = None


    def dirt(self):
        return self.__dirt_rep

    def __is_boundary(self, char):
        return char == self.__horizontal_boundary or char == self.__vertical_boundary or char == self.__new_line

    def __init__(self, world, path):
        self.__world = world
        self.__path = path
        self.__explored = {}
        self.__fringe = []
        self.__write_to_file("", False)

        for x, row in enumerate(self.__world):
            for y, data in enumerate(row):
                if not self.__is_boundary(data):
                    if data == "s":
                        self.__start = Node(x, y, data)
                        return

    def get_start(self):
        return self.__start

    def __find_adjacent_nodes(self, world, node):
        """
        :type world: list
        """
        row = node.x_coordinate()
        column = node.y_coordinate()
        adjacent_nodes = []
        left_node = world[row][column - 1]
        right_node = world[row][column + 1]
        top_node = world[row - 1][column]
        down_node = world[row + 1][column]
        if not self.__is_boundary(left_node):
            adjacent_nodes.append(Node(row, column - 1, left_node))
        if not self.__is_boundary(right_node):
            adjacent_nodes.append(Node(row, column + 1, right_node))
        if not self.__is_boundary(top_node):
            adjacent_nodes.append(Node(row - 1, column, top_node))
        if not self.__is_boundary(down_node):
            adjacent_nodes.append(Node(row + 1, column, down_node))
        return adjacent_nodes

    def add_to_fringe(self, node):
        self.__fringe.append(node)
        self.add_to_explored(node)

    def pop_from_fringe(self, index=0):
        return self.__fringe.pop(index)

    def fringe_len(self):
        return len(self.__fringe)

    def get_adjacent_nodes(self, node):
        return self.__find_adjacent_nodes(self.__world, node)

    def get_node_data(self, node_key):
        return self.__world[node_key].get_data()

    def add_to_explored(self, node):
        self.__explored[node.get_node_key()] = node

    def get_is_explored(self, node):
        return self.__explored.has_key(node.get_node_key())

    def __write_to_file(self, data, append=True):
        Util.write_to_file(self.__path, data, append)

    def update_world(self, node):
        return'''
        self.__world[node.x_coordinate()][node.y_coordinate()] = "0"
        data = ""
        for row in self.__world:
            for char in row:
                data += char
        self.__write_to_file(data + self.__new_line )
            '''