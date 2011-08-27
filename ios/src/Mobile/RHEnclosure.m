//
//  RHEnclosure.m
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

/// Model for a partition of RHNode objects. The notion of an enclosure pertains
/// more to pathfinding and navigation, and is not a top-level model element.

#import "RHEnclosure.h"

@implementation RHEnclosure

@synthesize internalNodes;
@synthesize edgeNodes;

- (id)init
{
    self = [super init];
    if (self) {
        // Initialization code here.
    }
    
    return self;
}

@end
