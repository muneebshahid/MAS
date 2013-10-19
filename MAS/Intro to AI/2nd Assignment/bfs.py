__author__ = 'muneeb'
from node import Node
from world import World


class BFS():
    __fringe = []
    __world = None
    __start = None

    def __init__(self, world, start):
        self.__world = world
        """:type : World"""
        self.__start = start
        """:type : Node"""
        self.__fringe.append(start)

    def search(self):
        self.__search(self.__world, self.__start)

    def __put_adjacent_nodes_on_fringe(self, node):
        adjacent_nodes = self.__world.get_adjacent_nodes(node)
        for adjacent_node in adjacent_nodes:
            if not self.__world.__explored.has_key(adjacent_node):
                self.__fringe.append(adjacent_node)

    def __search(self):
        while len(self.__fringe) > 0:
            node = self.__fringe.pop()
            if self.__world.is_explored(node):
                return
            else:
                node.is_explored = True
            self.__put_adjacent_nodes_on_fringe(node)





