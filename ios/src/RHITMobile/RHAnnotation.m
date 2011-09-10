//
//  RHAnnotation.m
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

#import "RHAnnotation.h"

@implementation RHAnnotation

@synthesize coordinate;
@synthesize annotationType;
@synthesize location;

- (RHAnnotation *) initWithLocation:(RHLocation *)newLocation
                     annotationType:(RHAnnotationType)newAnnotationType {
    double totalLatitude = 0;
    double totalLongitude = 0;
    int count = 0;
    
    for (RHNode *node in newLocation.boundaryNodes) {
        totalLatitude += node.latitude;
        totalLongitude += node.longitude;
        count ++;
    }
    
    CLLocationCoordinate2D center;
    center.latitude = totalLatitude / count;
    center.longitude = totalLongitude / count;
    
    return [self initWithLocation:newLocation
                       coordinate:center
                   annotationType:newAnnotationType];
    
}

- (RHAnnotation *) initWithLocation:(RHLocation *)newLocation
                         coordinate:(CLLocationCoordinate2D)newCoordinate
                       annotationType:(RHAnnotationType)newAnnotationType {
    self = [super init];
    
    if (self) {
        self.coordinate = newCoordinate;
        self.annotationType = newAnnotationType;
        self.location = newLocation;
    }
    
    return self;
}

@end
