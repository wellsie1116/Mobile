//
//  RHLocation.h
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
#import "RHBoundaryNode.h"

/// Representation of a canonical location, which may be more than a specific
/// point in space.

@interface RHLocation : NSObject

/// Human-readable name of this location
@property (nonatomic, retain) NSString *name;

/// RHNavigationNode objects that make up this location.
@property (nonatomic, retain) NSArray *navigationNodes;

/// RHBoundaryNode objects that define the border of this location.
@property (nonatomic, retain) NSArray *boundaryNodes;

/// RHLocation objects that are part of or enclosed by this location.
@property (nonatomic, retain) NSArray *enclosedLocations;

/// Initialize with all properties.
- (RHLocation *) initWithNavigationNodes:(NSArray *)navigationNodes
                           boundaryNodes:(NSArray *)boundaryNodes
                       enclosedLocations:(NSArray *)enclosedLocations;

@end
