__author__ = 'muneeb'

from node import Node


class World():
    __world = {}

    @staticmethod
    def __is_boundary(char):
        return char == "=" or char == "|"

    def __init__(self, world):
        for i, row in enumerate(world):
            for j, column in enumerate(row):
                if not self.__is_boundary(column):


        self.__world = world
        """:type : list"""

    def get_start_node(self, ):
        start = "s"
        for i, rows in enumerate(self.__world):
            for j, column in enumerate(rows):
                if column == start:
                    return Node(i, j)

    def get_adjacent_nodes(self, node):
        adjacent_nodes = []
        left_node = self.__world[node.x_coordinate - 1, node.y_coordinate]
        right_node = self.__world[node.x_coordinate + 1, node.y_coordinate]
        up_node = self.__world[node.x_coordinate, node.y_coordinate + 1]
        down_node = self.__world[node.x_coordinate, node.y_coordinate - 1]
        if not World.__is_boundary(left_node):
            adjacent_nodes.append(Node(node.x_coordinate - 1, node.y_coordinate))
        if not World.__is_boundary(right_node):
            adjacent_nodes.append(Node(node.x_coordinate + 1, node.y_coordinate))
        if not World.__is_boundary(up_node):
            adjacent_nodes.append(Node(node.x_coordinate, node.y_coordinate - 1))
        if not World.__is_boundary(down_node):
            adjacent_nodes.append(Node(node.x_coordinate, node.y_coordinate + 1))
        return adjacent_nodes