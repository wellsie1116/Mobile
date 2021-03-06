//
//  RHEnclosure.h
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

#import "RHNavigationNode.h"

/// Representation of a geographic partition of all RHNode objects.

@interface RHEnclosure : NSObject

/// RHNode objects internal to the enclosure that do not contribute to its edge.
@property (nonatomic, retain) NSArray *internalNodes;

/// RHNode objects that are part of the edge of the enclosure.
@property (nonatomic, retain) NSArray *edgeNodes;

/// Initialize with all properties.
- (RHEnclosure *) initWithInternalNodes:(NSArray *)internalNodes
                              edgeNodes:(NSArray *)edgeNodes;

@end
