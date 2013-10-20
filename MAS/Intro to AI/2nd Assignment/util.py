__author__ = 'muneeb'

from node import Node


class Util(object):
    @staticmethod
    def read_file(path):
        lines = []
        l = ""
        with open(path) as input_file:
            for line in input_file.readlines():
                lines.append(line)
            return lines