__author__ = 'muneeb'

from node import Node


class World():
    __world = None

    @staticmethod
    def __is_boundary(char):
        return char == "=" or char == "|"

    def __init__(self, world):
        self.__world = {}
        """:type : dictionary"""
        for x, row in enumerate(world):
            for y, point in enumerate(row):
                if not self.__is_boundary(point):
                    self.__world[Node(x, y, self.__get_adjacent_nodes(x, y))]

    def __get_start_node(self, ):
        start = "s"
        for i, rows in enumerate(self.__world):
            for j, column in enumerate(rows):
                if column == start:
                    return Node(i, j)

    def __get_adjacent_nodes(self, x_coordinate, y_coordinate):
        adjacent_nodes = []
        left_node = self.__world[x_coordinate - 1, y_coordinate]
        right_node = self.__world[x_coordinate + 1, y_coordinate]
        up_node = self.__world[x_coordinate, y_coordinate + 1]
        down_node = self.__world[x_coordinate, y_coordinate - 1]
        if not World.__is_boundary(left_node):
            adjacent_nodes.append(Node(x_coordinate - 1, y_coordinate))
        if not World.__is_boundary(right_node):
            adjacent_nodes.append(Node(x_coordinate + 1, y_coordinate))
        if not World.__is_boundary(up_node):
            adjacent_nodes.append(Node(x_coordinate, y_coordinate - 1))
        if not World.__is_boundary(down_node):
            adjacent_nodes.append(Node(x_coordinate, y_coordinate + 1))
        return adjacent_nodes