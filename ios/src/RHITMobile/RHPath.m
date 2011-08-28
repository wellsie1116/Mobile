//
//  RHPath.m
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

#import "RHPath.h"

@implementation RHPath

@synthesize node1;
@synthesize node2;
@synthesize pathType;

- (RHPath *) initWithNode1:(RHNavigationNode *)newNode1
                     node2:(RHNavigationNode *)newNode2
                  pathType:(RHPathType)newPathType {
    self = [super init];
    self.node1 = newNode1;
    self.node2 = newNode2;
    self.pathType = pathType;
    return self;
}

- (void) dealloc {
    [self.node1 release];
    [self.node2 release];
    [super dealloc];
}

@end
