//
//  RHNode.m
//  Mobile
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "RHNode.h"

@implementation RHNode

@synthesize latitude;
@synthesize longitude;
@synthesize indoors;
@synthesize floor;

- (id)init
{
    self = [super init];
    if (self) {
        // Initialization code here.
    }
    
    return self;
}

@end
