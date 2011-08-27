//
//  RHPath.m
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "RHPath.h"

@implementation RHPath

@synthesize node1;
@synthesize node2;
@synthesize pathType;

- (id) initWithNode1:(RHNode *)newNode1 
               node2:(RHNode *)newNode2 
            pathType:(RHPathType)newPathType {
    self = [super init];
    if (self) {
        self.node1 = newNode1;
        self.node2 = newNode2;
        self.pathType = newPathType;
    }
    
    return self;
}

- (void) dealloc {
    [self.node1 release];
    [self.node2 release];
    [super dealloc];
}

@end
