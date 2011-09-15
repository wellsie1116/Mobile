//
//  RHAnnotationView.m
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

#import "RHAnnotationView.h"
#import "RHAnnotation.h"

@implementation RHAnnotationView

@synthesize textView;

- (id)initWithAnnotation:(id<MKAnnotation>)inAnnotation
         reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithAnnotation:inAnnotation
                     reuseIdentifier:reuseIdentifier];
    RHAnnotation *annotation = (RHAnnotation *)inAnnotation;
    
    switch (annotation.annotationType) {
        case RHAnnotationTypeText:
            self.frame = CGRectMake(0, 0, 100, 50);
            self.backgroundColor = [UIColor clearColor];
            
            textView = [[RHMapLabel alloc] initWithFrame:CGRectMake(2, 2, 96, 46)];
            textView.backgroundColor = [UIColor clearColor];
            textView.text = annotation.location.name;
            textView.font = [UIFont fontWithName:@"Arial" size:10];
            textView.textColor = [UIColor whiteColor];
            textView.textAlignment = UITextAlignmentCenter;
            [self addSubview:textView];
            break;
        case RHAnnotationTypePolygon:
            // TODO: Add polygon rendering
            break;
        case RHAnnotationTypeTextAndPolygon:
            // TODO: Add polygon rendering
            self.frame = CGRectMake(0, 0, 100, 50);
            self.backgroundColor = [UIColor clearColor];
            
            textView = [[RHMapLabel alloc] initWithFrame:CGRectMake(2, 2, 96, 46)];
            textView.backgroundColor = [UIColor clearColor];
            textView.text = annotation.location.name;
            textView.font = [UIFont fontWithName:@"Arial-BoldMT" size:10];
            textView.textColor = [UIColor whiteColor];
            textView.textAlignment = UITextAlignmentCenter;
            [self addSubview:textView];
            break;
    }
    
    return self;
}

@end
