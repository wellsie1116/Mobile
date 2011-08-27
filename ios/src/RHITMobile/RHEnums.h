//
//  RHEnums.h
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

/// Various enumerations pertaining to the model layer.

#ifndef RHITMobile_Header_h
#define RHITMobile_Header_h

/// Represents the notion of floors in a building.
typedef enum _RHFloor {
    RHFLOOR_NONE,
    RHFLOOR_SUBBASEMENT,
    RHFLOOR_BASEMENT,
    RHFLOOR_FIRST,
    RHFLOOR_SECOND,
    RHFLOOR_THIRD,
    RHFLOOR_FOURTH,
    RHFLOOR_FIFTH
} RHFloor;

/// Represents the notion of how traversable a path is
typedef enum _RHPathType {
    RHPATHTYPE_STANDARD,
    RHPATHTYPE_STAIRS,
    RHPATHTYPE_ELEVATOR
} RHPathType;

#endif
