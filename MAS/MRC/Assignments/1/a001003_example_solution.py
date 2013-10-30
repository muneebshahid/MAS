#!/usr/bin/env python
# -*- coding: utf-8 -*- 
import matplotlib.pyplot as plt
import mpl_toolkits.mplot3d
import numpy as np

def create_lidar_to_world_matrix():
    # The assignment gives no orientation for the robot with respect to the
    # world frame, so the rotation matrix will be the identity matrix. Given that we
    # want to create a homogenuous 4x4 transformation matrix, we can just start with
    # a 4x4 identity matrix here
    T_robot_to_world = np.eye(4)

    # R will now look like this:
    # [[ 1.,  0.,  0.,  0.],
    #  [ 0.,  1.,  0.,  0.],
    #  [ 0.,  0.,  1.,  0.],
    #  [ 0.,  0.,  0.,  1.]]
    # ...so we now add the translational offset of the robot in W. From the 
    # assignment text, we know the robot is located at [3.7, 3.5, 0]
    robot_ref_frame_origin = np.array([3.7, 3.5, 0])
   
    # At this point, we can combine the rotation and offset into a transformation
    # matrix. We have to augment the translational offset to obtain homogenuous
    # coordinates. Normally, you'd use 1 here, but since we already included the 1
    # when we created the 4x4 identity matrix, we just put a 0 here
    T_robot_to_world[:, -1] += np.append(robot_ref_frame_origin, [0])
    
    # Ok, now we can go back and forth from robot frame to the world frame. Let's
    # add the LIDAR frame to the transformation chain now. The assignment clearly
    # states the orientation of the LIDAR is identical to that of the base, so we
    # again start with a 4x4 identity matrix, as there is no rotation involved
    T_lidar_to_robot = np.eye(4)
    
    # Convert the polar coordinates of the LIDAR frame to a translation offset
    lidar_ref_frame_origin = np.array([
        0.43 * np.cos(np.radians(37)), 0.43 * np.sin(np.radians(37)), 0.5, 0
    ])
    
    # ...combine both rotation and translation
    T_lidar_to_robot[:, -1] += lidar_ref_frame_origin
    
    # Now, return a matrix containing the complete transformation from LIDAR to world
    return np.dot(T_robot_to_world, T_lidar_to_robot)


def object_coordinates_from_lidar_data(lidar_data):
    # np.where has the default condition np.nonzero, so if you pass an array to where
    # it will return the indices of the array at which the value of the array is 
    # nonzero. You will get a tuple of indices, one list of indices per dimension
    indices_of_object_sightings = np.where(lidar_data)

    # In our case, the second list corresponds to the angle in degrees
    angles_of_object_sightings = np.radians(indices_of_object_sightings[1])
    
    # The values of the LIDAR data are actually distances, so let's read all
    # the distances of each object sighting now
    distances_of_object_sightings = lidar_data[indices_of_object_sightings]
    
    # Convert from polar coordinates to cartesian coordinates
    x = distances_of_object_sightings * np.cos(angles_of_object_sightings) 
    y = distances_of_object_sightings * np.sin(angles_of_object_sightings) 
    return np.hstack((
        x[np.newaxis].T,
        y[np.newaxis].T
    ))

if __name__ == "__main__":
    # This is a script, so turn interactive mode off
    plt.ioff()
    
    # Load the LIDAR data as observed by the scanner
    lidar_data = np.load("lidar_scan.npy")

    # Obtain X/Y coordinates of the object in the LIDAR frame
    object_points = object_coordinates_from_lidar_data(lidar_data)
    # Add Z coordinates
    object_points = np.hstack((
        object_points,
        np.zeros(shape=(object_points.shape[0], 1)),
        np.ones(shape=(object_points.shape[0], 1)), # homogenuous coordinates
    ))

    # Obtain a 4x4 transformation matrix from LIDAR to the world reference frame
    transformation_lidar_to_world = create_lidar_to_world_matrix()

    # Transform all points to the world frame
    observation_in_world_frame = np.dot(object_points, transformation_lidar_to_world.T)
    
    # Create a plot
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    lx, ly, lz, lw = observation_in_world_frame.T
    ax.plot(lx, ly, lz, "ko")
    ax.plot([3.7], [3.5], [0], "ro")
    fig.savefig("mrc_a001003.png", format="PNG")
    
    