//
//  RHPath.h
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

#import <Foundation/Foundation.h>

#import "RHEnums.h"
#import "RHNavigationNode.h"

/// Representation of a traversable connection between two RHNode objects.

@interface RHPath : NSObject

/// First RHNode that this path connects.
@property (nonatomic, retain) RHNavigationNode *node1;

/// Second RHNode that this path connects.
@property (nonatomic, retain) RHNavigationNode *node2;

/// Any special characteristics of this path.
@property (nonatomic, assign) RHPathType pathType;

/// Initialize with all properties.
- (RHPath *) initWithNode1:(RHNavigationNode *)node1
                     node2:(RHNavigationNode *)node2
                  pathType:(RHPathType)pathType;

@end
