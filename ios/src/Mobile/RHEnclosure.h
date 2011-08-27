//
//  RHEnclosure.h
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//
 
/// Model for a partition of RHNode objects. The notion of an enclosure pertains
/// more to pathfinding and navigation, and is not a top-level model element.

#import <Foundation/Foundation.h>

@interface RHEnclosure : NSObject

/// Nodes that are completely contained in the Enclosure and do not contribute
/// to its structure.
@property (nonatomic, retain) NSArray *internalNodes;

/// Nodes that make up the edge of the Enclosure and directly affect its
/// structure.
@property (nonatomic, retain) NSArray *edgeNodes;

@end