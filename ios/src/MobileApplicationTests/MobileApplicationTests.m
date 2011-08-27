//
//  MobileApplicationTests.m
//  MobileApplicationTests
//
//  Created by Jimmy Theis on 8/26/11.
//  Copyright 2011 Rose-Hulman Institute of Technology. All rights reserved.
//

#import "MobileApplicationTests.h"
#import "MobileAppDelegate.h"

@implementation MobileApplicationTests

- (void)setUp
{
    [super setUp];
    
    // Set-up code here.
}

- (void) testAppDelegate {
    id appDelegate = [[UIApplication sharedApplication] delegate];
    STAssertNotNil(appDelegate, @"Could not find the app delegate");
}

- (void)tearDown
{
    // Tear-down code here.
    
    [super tearDown];
}

@end
