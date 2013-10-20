__author__ = 'muneeb'

from node import Node


class World():
    _start_rep = "s"
    _dirt_rep = "*"
    __world = None
    __start = None
    __vertical_boundary = "|"
    __horizontal_boundary = "="
    __new_line = "\n"

    def __is_boundary(self, char):
        return char == self.__horizontal_boundary or char == self.__vertical_boundary or char == self.__new_line

    def __init__(self, world):
        self.__world = {}
        """:type : dictionary"""
        for x, row in enumerate(world):
            for y, point in enumerate(row):
                if not self.__is_boundary(point):
                    node_key = self.__get_node_position(x, y)
                    self.__world[node_key] = Node(node_key, self.__find_adjacent_nodes(world, x, y))
                    if point == "s":
                        self.__start = node_key

    def get_start(self):
        return self.__start

    def __get_node_position(self, x, y):
        return str(x) + str(y)

    def __find_adjacent_nodes(self, world, x_coordinate, y_coordinate):
        adjacent_nodes = []
        left_node = world[x_coordinate - 1, y_coordinate]
        right_node = world[x_coordinate + 1, y_coordinate]
        up_node = world[x_coordinate, y_coordinate + 1]
        down_node = world[x_coordinate, y_coordinate - 1]
        if not World.__is_boundary(left_node):
            adjacent_nodes.append(self.__get_node_position(x_coordinate - 1, y_coordinate))
        if not World.__is_boundary(right_node):
            adjacent_nodes.append(self.__get_node_position(x_coordinate + 1, y_coordinate))
        if not World.__is_boundary(up_node):
            adjacent_nodes.append(self.__get_node_position(x_coordinate, y_coordinate - 1))
        if not World.__is_boundary(down_node):
            adjacent_nodes.append(self.__get_node_position(x_coordinate, y_coordinate + 1))
        return adjacent_nodes

    def get_is_explored(self, node_key):
        return self.__world[node_key].is_explored

    def set_is_explored(self, node_key, explored):
        self.__world[node_key].is_explored = explored

    def get_adjacent_nodes(self, node_key):
        self.__world[node_key].get_adjacent_nodes()

    def get_node_data(self, node_key):
        self.__world[node_key].get_data()