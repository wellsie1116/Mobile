//
//  RHNode.m
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "RHNode.h"

@implementation RHNode

@synthesize latitude = latitude;
@synthesize longitude = longitude;
@synthesize indoors = indoors;
@synthesize floor = floor;

- (id)initWithLatitude:(NSDecimalNumber *)newLatitude
             longitude:(NSDecimalNumber *)newLongitude
               indoors:(BOOL)newIndoors 
                 floor:(RHFloor) newFloor {
    self = [super init];
    if (self) {
        self.latitude = newLatitude;
        self.longitude = newLongitude;
        self.indoors = newIndoors;
        self.floor = newFloor;
    }
    
    return self;
}

- (void) dealloc {
    [latitude release];
    [longitude release];
    [super dealloc];
}

@end
