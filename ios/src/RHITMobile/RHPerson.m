//
//  RHPerson.m
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

#import "RHPerson.h"

@implementation RHPerson

@synthesize location;
@synthesize firstName;
@synthesize lastName;

/// Initialize with all properties
- (RHPerson *)initWithLocation:(RHLocation *)newLocation
                     firstName:(NSString *)newFirstName
                      lastName:(NSString *)newLastName {
    self = (RHPerson *)[super init];
    
    if (self) {
        self.location = newLocation;
        self.firstName = newFirstName;
        self.lastName = newLastName;
    }
    
    return self;
}

- (void) dealloc {
    [location release];
    [firstName release];
    [lastName release];
    [super dealloc];
}

@end
