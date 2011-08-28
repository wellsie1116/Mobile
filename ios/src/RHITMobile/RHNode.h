//
//  RHNode.h
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

/// Representation of a node, or single point in space. RHNode is not meant
/// to be used directly, but rather to be subclassed, specifically into
/// RHNavigationNode and RHBoundaryNode.

@interface RHNode : NSObject

/// Latitude coordinate for this node.
@property (nonatomic, assign) double latitude;

/// Longitude coordinate for this node.
@property (nonatomic, assign) double longitude;

/// Initialize with all properties
- (RHNode *) initWithLatitude:(double)latitude 
                    longitude:(double)longitude;

@end
