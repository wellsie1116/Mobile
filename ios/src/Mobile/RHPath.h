//
//  RHPath.h
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

/// Model representing the connection between two Nodes. A Path is essentially
/// an edge in the graph of all Nodes.

#import <Foundation/Foundation.h>
#import "RHNode.h"
#import "RHEnums.h"

@interface RHPath : NSObject

/// First RHNode that this path connects to
@property (nonatomic, retain) RHNode *node1;

/// Second RHNode that this path connects to
@property (nonatomic, retain) RHNode *node2;

/// What type of path this is (i.e. does this path contain stairs, elevator).
@property (nonatomic, assign) RHPathType pathType;

/// Initialize with all properties.
- (id) initWithNode1:(RHNode *)newNode1 
               node2:(RHNode *)newNode2 
            pathType:(RHPathType)newPathType;

@end
