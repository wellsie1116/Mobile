//
//  RHNavigationNodeTests.m
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

#import "RHNavigationNodeTests.h"

@implementation RHNavigationNodeTests

- (void) testInitWithAllProperties {
    RHNavigationNode *node = [RHNavigationNode alloc];
    node = [node initWithLatitude:1
                        longitude:2
                          indoors:YES
                            floor:RHFLOOR_FOURTH];
    
    STAssertEquals(node.latitude, 1.0,
                   @"RHNavigationNode.latitude not properly set");
    STAssertEquals(node.longitude, 2.0,
                   @"RHNavigationNode.longitude not properly set");
    STAssertEquals(node.indoors, YES, 
                   @"RHNavigationNode.indoors not properly set");
    STAssertEquals(node.floor, RHFLOOR_FOURTH, 
                   @"RHNavigationNode.floor not properly set");
}

- (void) testInitWithAllPropertiesInPlace {
    RHNavigationNode *node = [RHNavigationNode alloc];
    [node initWithLatitude:3
                 longitude:4
                   indoors:YES
                     floor:RHFLOOR_FIFTH];
    
    STAssertEquals(node.latitude, 3.0,
                   @"RHNavigationNode.latitude not properly set");
    STAssertEquals(node.longitude, 4.0,
                   @"RHNavigationNode.longitude not properly set");
    STAssertEquals(node.indoors, YES, 
                   @"RHNavigationNode.indoors not properly set");
    STAssertEquals(node.floor, RHFLOOR_FIFTH, 
                   @"RHNavigationNode.floor not properly set");
}

@end
