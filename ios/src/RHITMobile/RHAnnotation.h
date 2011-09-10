//
//  RHAnnotation.h
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
#import <MapKit/MapKit.h>

#import "RHLocation.h"

typedef enum {
    RHAnnotationTypeText,
    RHAnnotationTypePolygon,
    RHAnnotationTypeTextAndPolygon
} RHAnnotationType;

@interface RHAnnotation : NSObject <MKAnnotation>

/// The "center point" of sorts for this location
@property (nonatomic, assign) CLLocationCoordinate2D coordinate;

/// Determines whether or not text or polygons will be rendered
@property (nonatomic, assign) RHAnnotationType annotationType;

/// Location model to pull data from
@property (nonatomic, retain) RHLocation *location;

/// Initialize with only an RHLocation and RHAnnotationType. This automatically
/// tries to guess the center point of the RHLocation.
- (RHAnnotation *) initWithLocation:(RHLocation *)location
                     annotationType:(RHAnnotationType)annotationType;

/// Initialize with an RHLocation, CLLocationCoordinate2D, and RHAnnotationType.
/// This allows you to set the "center point" manually.
- (RHAnnotation *) initWithLocation:(RHLocation *)location
                         coordinate:(CLLocationCoordinate2D)coordinate
                     annotationType:(RHAnnotationType)annotationType;

@end
