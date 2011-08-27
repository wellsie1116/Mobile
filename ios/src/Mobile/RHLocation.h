//
//  RHLocation.h
//  Rose-Hulman Mobile Campus Directory
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

/// Model representing a Rose-Hulman location. This is not necessarily a
/// specific point in space, though it very well could be. This notion of a
/// location correlates more to the human notion of a location, which could be
/// as large as an academic hall or as small as a statue.

#import <Foundation/Foundation.h>

@interface RHLocation : NSObject

/// The actual RHNode objects that represent this location.
@property (nonatomic, retain) NSArray *nodes; 

/// Any additional RHLocation objects that are enclosed by this location.
@property (nonatomic, retain) NSArray *enclosedLocations;

/// Initialize with all properties
- (id) initWithNodes:(NSArray *)nodes
   enclosedLocations:(NSArray *)enclosedlocations;

@end
