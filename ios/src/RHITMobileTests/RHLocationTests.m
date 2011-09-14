//
//  RHLocationTests.m
//  RHIT Mobile Campus Directory
//
//  Copyright 2011 Rose-Hulman Institute of Technology
// 
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//

#import "RHLocationTests.h"

@implementation RHLocationTests

- (void) testInitWithAllProperties {
    RHNavigationNode *node1 = [RHNavigationNode alloc];
    RHNavigationNode *node2 = [RHNavigationNode alloc];
    
    NSArray *navigationNodes = [NSArray alloc];
    navigationNodes = [navigationNodes initWithObjects:node1, node2, nil];
    
    RHBoundaryNode *node3 = [RHBoundaryNode alloc];
    RHBoundaryNode *node4 = [RHBoundaryNode alloc];
    
    NSArray *boundaryNodes = [NSArray alloc];
    boundaryNodes = [boundaryNodes initWithObjects:node3, node4, nil];
    
    RHLocation *location1 = [RHLocation alloc];
    RHLocation *location2 = [RHLocation alloc];
    
    NSArray *enclosed = [NSArray alloc];
    enclosed = [enclosed initWithObjects:location1, location2, nil];
    
    [enclosed containsObject:location1];
    
    RHLocation *location = [RHLocation alloc];
    location = [location initWithName:@"Test Location"
                      navigationNodes:navigationNodes
                        boundaryNodes:boundaryNodes
                    enclosedLocations:enclosed];
    
    STAssertTrue([location.navigationNodes containsObject: node1],
                 @"Navigation node missing");
    STAssertTrue([location.navigationNodes containsObject: node2],
                 @"Navigation node missing");
    
    STAssertTrue([location.boundaryNodes containsObject: node3],
                 @"Boundary node missing");
    STAssertTrue([location.boundaryNodes containsObject: node4],
                 @"Boundary node missing");
    
    STAssertTrue([location.enclosedLocations containsObject: location1],
                 @"Enclosed location missing");
    STAssertTrue([location.enclosedLocations containsObject: location2],
                 @"Enclosed location missing");
    
}

- (void) testInitWithAllPropertiesInPlace {
    RHNavigationNode *node1 = [RHNavigationNode alloc];
    RHNavigationNode *node2 = [RHNavigationNode alloc];
    
    NSArray *navigationNodes = [NSArray alloc];
    navigationNodes = [navigationNodes initWithObjects:node1, node2, nil];
    
    RHBoundaryNode *node3 = [RHBoundaryNode alloc];
    RHBoundaryNode *node4 = [RHBoundaryNode alloc];
    
    NSArray *boundaryNodes = [NSArray alloc];
    boundaryNodes = [boundaryNodes initWithObjects:node3, node4, nil];
    
    RHLocation *location1 = [RHLocation alloc];
    RHLocation *location2 = [RHLocation alloc];
    
    NSArray *enclosed = [NSArray alloc];
    enclosed = [enclosed initWithObjects:location1, location2, nil];
    
    [enclosed containsObject:location1];
    
    RHLocation *location = [RHLocation alloc];
    [location initWithName:@"Test Location"
           navigationNodes:navigationNodes
             boundaryNodes:boundaryNodes
         enclosedLocations:enclosed];
    
    STAssertTrue([location.navigationNodes containsObject: node1],
                 @"Navigation node missing");
    STAssertTrue([location.navigationNodes containsObject: node2],
                 @"Navigation node missing");
    
    STAssertTrue([location.boundaryNodes containsObject: node3],
                 @"Boundary node missing");
    STAssertTrue([location.boundaryNodes containsObject: node4],
                 @"Boundary node missing");
    
    STAssertTrue([location.enclosedLocations containsObject: location1],
                 @"Enclosed location missing");
    STAssertTrue([location.enclosedLocations containsObject: location2],
                 @"Enclosed location missing");
}

@end
