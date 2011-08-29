//
//  RHPersonTests.m
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

#import "RHPersonTests.h"

@implementation RHPersonTests

- (void) testInitWithAllProperties {
    RHLocation *location = [RHLocation alloc];
    
    RHPerson *person = [[RHPerson alloc] initWithLocation:location
                                                firstName:@"Jimmy"
                                                 lastName:@"Theis"];
    
    STAssertEquals(person.location, location,
                   @"RHPerson.location not set properly");
    STAssertEquals(person.firstName, @"Jimmy",
                   @"RHPerson.firstName not set properly");
    STAssertEquals(person.lastName, @"Theis",
                   @"RHPerson.lastName not set properly");
}

- (void) testInitWithAllPropertiesInPlace {
    RHLocation *location = [RHLocation alloc];
    
    RHPerson *person = [RHPerson alloc];
    [person initWithLocation:location
                   firstName:@"Jimmy"
                    lastName:@"Theis"];
    
    STAssertEquals(person.location, location,
                   @"RHPerson.location not set properly");
    STAssertEquals(person.firstName, @"Jimmy",
                   @"RHPerson.firstName not set properly");
    STAssertEquals(person.lastName, @"Theis",
                   @"RHPerson.lastName not set properly");
}

@end
