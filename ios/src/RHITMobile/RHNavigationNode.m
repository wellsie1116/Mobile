//
//  RHNavigationNode.m
//  RHITMobile
//
//  Created by Jimmy Theis on 8/28/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "RHNavigationNode.h"

@implementation RHNavigationNode

@synthesize indoors;
@synthesize floor;

- (RHNode *) initWithLatitude:(double)newLatitude 
                    longitude:(double)newLongitude 
                      indoors:(BOOL)newIndoors 
                        floor:(RHFloor)newFloor {
    self = (RHNavigationNode *)[super initWithLatitude:newLatitude
                                             longitude:newLongitude];
    if (self) {
        self.indoors = newIndoors;
        self.floor = newFloor;
    }
    
    return self;
}


@end
