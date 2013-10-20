__author__ = 'muneeb'

from node import Node


class Util(object):
    @staticmethod
    def read_file(path):
        world = []
        with open(path) as input_file:
            for line in input_file.readlines():
                charList = []
                for char in line:
                    charList.append(char)
                world.append(charList)
            return world

    @staticmethod
    def __open_writer(path, append):
        return open(path, "a") if append else open(path, "w")

    @staticmethod
    def write_to_file(path, data, append):
        writer = Util.__open_writer(path, append)
        writer.write(data)
        writer.close()
        return