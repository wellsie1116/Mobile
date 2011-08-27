//
//  RHLocation.m
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "RHLocation.h"

@implementation RHLocation

@synthesize nodes;
@synthesize enclosedLocations;

- (id) initWithNodes:(NSArray *)newNodes 
   enclosedLocations:(NSArray *)newLocations {
    self = [super init];
    if (self) {
        self.nodes = newNodes;
        self.enclosedLocations = newLocations;
    }
    
    return self;
}

- (void) dealloc {
    [self.nodes release];
    [self.enclosedLocations release];
    [super dealloc];
}

@end
