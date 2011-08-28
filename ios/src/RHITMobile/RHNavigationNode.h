//
//  RHNavigationNode.h
//  RHITMobile
//
//  Created by Jimmy Theis on 8/28/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "RHNode.h"

@interface RHNavigationNode : RHNode

/// Whether or not this node is indoors.
@property (nonatomic, assign) BOOL indoors;

/// Which floor this node is on, if applicable.
@property (nonatomic, assign) RHFloor floor;

/// Initialize with all properties.
- (RHNode *) initWithLatitude:(double)latitude 
                    longitude:(double)longitude 
                      indoors:(BOOL)indoors 
                        floor:(RHFloor)floor;

@end
