//
//  RHNodeTests.m
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

#import "RHNodeTests.h"

@implementation RHNodeTests

- (void) testInitWithAllProperties {
    RHNode *node = [[RHNode alloc] initWithLatitude:1 
                                          longitude:2];
    
    STAssertEquals(node.latitude, 1.0,
                   @"RHNode.latitude not properly set");
    STAssertEquals(node.longitude, 2.0,
                   @"RHNode.longitude not properly set");
}

- (void) testInitWithAllPropertiesInPlace {
    RHNode *node = [RHNode alloc];
    [node initWithLatitude:3 longitude:4];
    
    STAssertEquals(node.latitude, 3.0,
                   @"RHNode.latitude not properly set");
    STAssertEquals(node.longitude, 4.0,
                   @"RHNode.longitude not properly set");
}

@end
