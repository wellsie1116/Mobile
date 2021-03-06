//
//  RHPathTests.h
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

#import <SenTestingKit/SenTestingKit.h>

#import "RHPath.h"

/// Unit tests (logic) for RHPath.

@interface RHPathTests : SenTestCase

/// Test basic initialization.
- (void) testInitWithAllProperties;

/// Test basic initialization in place.
- (void) testInitWithAllPropertiesInPlace;

/// Verify that the generated NSArray of nodes is initially populated correctly.
- (void) testNodesArrayIsPopulatedCorrectly;

/// Verify thta the generated NSArray of nodes is updated correctly.
- (void) testNodesArrayIsUpdatedCorrectly;

@end
