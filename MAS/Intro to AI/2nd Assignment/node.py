__author__ = 'muneeb'


class Node(object):
    x_coordinate = None
    y_coordinate = None
    adjacent_nodes = None

    def __init__(self, x_coordinate, y_coordinate, _is_explored=False):
        self.x_coordinate = x_coordinate
        self.y_coordinate = y_coordinate

    def equals(self, node):
        return self.x_coordinate == node.x_coordinate and self.y_coordinate == node.y_coordinate