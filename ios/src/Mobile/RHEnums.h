//
//  RHEnums.h
//  Mobile
//
//  Created by Jimmy Theis on 8/27/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#ifndef Mobile_RHEnums_h
#define Mobile_RHEnums_h

/// An enum for floors
typedef enum _RHFloor {
    RHFLOOR_SUBBASEMENT,
    RHFLOOR_BASEMENT,
    RHFLOOR_FIRST,
    RHFLOOR_SECOND,
    RHFLOOR_THIRD,
    RHFLOOR_FOURTH
} RHFloor;

/// An enum for the types of paths that exist
typedef enum _RHPathType {
    RHPATHTYPE_STANDARD,
    RHPATHTYPE_STAIRS,
    RHPATHTYPE_ELEVATOR
} RHPathType;


#endif
