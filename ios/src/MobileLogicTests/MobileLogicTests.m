//
//  MobileLogicTests.m
//  MobileLogicTests
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "MobileLogicTests.h"
#import "RHNode.h"
#import "RHLocation.h"
#import "RHPath.h"
#import "RHEnclosure.h"
#import "RHPerson.h"
#import "RHEnums.h"

@implementation MobileLogicTests

- (void)setUp {
    [super setUp];
    
    // Set-up code here.
}

- (void)tearDown {
    // Tear-down code here.
    
    [super tearDown];
}

/// Create and initialize each type of object, just to make sure nothing
/// blows up or is nil.
- (void) testBasicInitialization {
    RHNode *node1 = [[RHNode alloc] initWithLatitude:1
                                           longitude:2
                                             indoors:NO
                                               floor:RHFLOOR_FIRST];
    RHNode *node2 = [[RHNode alloc] initWithLatitude:1
                                           longitude:2
                                             indoors:NO
                                               floor:RHFLOOR_FIRST];
    STAssertNotNil(node1, @"New RHNode node1 was nil");
    STAssertNotNil(node2, @"New RHNode node2 was nil");
    
    NSArray *nodes = [[NSArray alloc] initWithObjects:node1, node2, nil];
    
    RHLocation *location = [[RHLocation alloc] initWithNodes:nodes 
                                           enclosedLocations:nil];
    
    STAssertNotNil(location, @"New RHLocation was nil");
    
    RHPath *path = [[RHPath alloc] initWithNode1:node1 
                                           node2:node2 
                                        pathType:RHPATHTYPE_STANDARD];
    
    STAssertNotNil(path, @"New RHPath was nil");
    
    // TODO: Create and initialize RHEnclosure object
    
    // TODO: Create and initialize RHPerson object
    
    [node1 release];
    [node2 release];
    [nodes release];
    [path release];
}

@end
