//
//  RHEnclosureTests.m
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

#import "RHEnclosureTests.h"

@implementation RHEnclosureTests

- (void) testInitWithAllProperties {
    RHNavigationNode *node1 = [RHNavigationNode alloc];
    RHNavigationNode *node2 = [RHNavigationNode alloc];
    RHNavigationNode *node3 = [RHNavigationNode alloc];
    
    NSArray *internals = [NSArray alloc];
    internals = [internals initWithObjects:node1, node2, node3, nil];
    
    RHNavigationNode *node4 = [RHNavigationNode alloc];
    RHNavigationNode *node5 = [RHNavigationNode alloc];
    
    NSArray *edges = [NSArray alloc];
    edges = [edges initWithObjects:node4, node5, nil];
    
    RHEnclosure *enclosure = [RHEnclosure alloc];
    enclosure = [enclosure initWithInternalNodes:internals
                                       edgeNodes:edges];
    
    STAssertTrue([enclosure.internalNodes containsObject:node1],
                 @"Internal node missing");
    STAssertTrue([enclosure.internalNodes containsObject:node2],
                 @"Internal node missing");
    STAssertTrue([enclosure.internalNodes containsObject:node3],
                 @"Internal node missing");
    
    STAssertTrue([enclosure.edgeNodes containsObject:node4],
                 @"Edge node missing");
    STAssertTrue([enclosure.edgeNodes containsObject:node5],
                 @"Edge node missing");
}

- (void) testInitWithAllPropertiesInPlace {
    RHNavigationNode *node1 = [RHNavigationNode alloc];
    RHNavigationNode *node2 = [RHNavigationNode alloc];
    RHNavigationNode *node3 = [RHNavigationNode alloc];
    
    NSArray *internals = [NSArray alloc];
    internals = [internals initWithObjects:node1, node2, node3, nil];
    
    RHNavigationNode *node4 = [RHNavigationNode alloc];
    RHNavigationNode *node5 = [RHNavigationNode alloc];
    
    NSArray *edges = [NSArray alloc];
    edges = [edges initWithObjects:node4, node5, nil];
    
    RHEnclosure *enclosure = [RHEnclosure alloc];
    [enclosure initWithInternalNodes:internals
                           edgeNodes:edges];
    
    STAssertTrue([enclosure.internalNodes containsObject:node1],
                 @"Internal node missing");
    STAssertTrue([enclosure.internalNodes containsObject:node2],
                 @"Internal node missing");
    STAssertTrue([enclosure.internalNodes containsObject:node3],
                 @"Internal node missing");
    
    STAssertTrue([enclosure.edgeNodes containsObject:node4],
                 @"Edge node missing");
    STAssertTrue([enclosure.edgeNodes containsObject:node5],
                 @"Edge node missing");
}

@end
