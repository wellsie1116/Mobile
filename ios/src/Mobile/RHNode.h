//
//  RHNode.h
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

/// Model representing a single point in space at Rose-Hulman. Nodes will often
/// correlate with points of interest, but could also be used as boundaries
/// or navigation points. The notion of a Node is more a utility object in this
/// case: a building block for more intuitive models.

#import <Foundation/Foundation.h>
#import "RHEnums.h"

@interface RHNode : NSObject

/// Latutide coordinate of the Node
@property (nonatomic, assign) double latitude;

/// Longitude coordinate of the Node
@property (nonatomic, assign) double longitude;

/// Whether or not this node is an indoor location.
@property (nonatomic, assign) BOOL indoors;

/// Which floor the node is on
@property (nonatomic, assign) RHFloor floor;

/// Initialize with all properties.
- (id)initWithLatitude:(double)latutide
             longitude:(double)longitude
               indoors:(BOOL)indoors
                 floor:(RHFloor)floor;

@end
