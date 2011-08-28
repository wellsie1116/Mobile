//
//  RHPathTests.m
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

#import "RHPathTests.h"

@implementation RHPathTests

- (void) testInitWithAllProperties {
    RHNode *node1 = [[RHNode alloc] initWithLatitude:1 
                                           longitude:2 
                                             indoors:YES 
                                               floor:RHFLOOR_FIRST];
    RHNode *node2 = [[RHNode alloc] initWithLatitude:3 
                                           longitude:4 
                                             indoors:NO 
                                               floor:RHFLOOR_SECOND];
    RHPath *path = [[RHPath alloc] initWithNode1:node1
                                           node2:node2
                                        pathType:RHPATHTYPE_STANDARD];
    STAssertEquals(path.node1, node1, @"RHPath.node1 not set correctly");
    STAssertEquals(path.node2, node2, @"RHPath.node2    not set correctly");
    STAssertEquals(path.pathType, RHPATHTYPE_STANDARD,
                   @"RHPath.pathType not set properly");
}

@end
