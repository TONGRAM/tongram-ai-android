pipeline {
    agent {
        docker { 
            image 'toncorp/android-builder:1.0.2'
            args '-v $HOME/.gradle:/root/.gradle'
        }
    }

    environment {
        ANDROID_HOME = "/usr/local/android-sdk-linux"
        //S3
        S3_BUCKET = "tongram"
        AWS_REGION = "ap-southeast-1"
        S3_PATH = "AppBuild/android-releases" 
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Setup Permission') {
            steps {
                sh 'chmod +x gradlew'
            }
        }

        // stage('Unit Test') {
        //     steps {
        //         sh './gradlew testDebugUnitTest'
        //     }
        // }

        stage('Check Info') {
            steps {
                sh 'java -version'
                sh 'ls $ANDROID_HOME'
            }
        }

        stage('Build Release') {
            steps {
                withCredentials([
                    file(credentialsId: 'android-keystore-file', variable: 'KEYSTORE_FILE'),
                    string(credentialsId: 'STORE_PASSWORD', variable: 'STORE_PASS'),
                    string(credentialsId: 'KEY_ALIAS', variable: 'KEY_ALIAS'),
                    string(credentialsId: 'KEY_PASSWORD', variable: 'KEY_PASS')
                ]) {
                    sh """
                        echo "Building Release APK and AAB..."
                    """
                    // sh """
                    //     ./gradlew assembleRelease bundleRelease \
                    //     -Pandroid.injected.signing.store.file='$KEYSTORE_FILE' \
                    //     -Pandroid.injected.signing.store.password='$STORE_PASS' \
                    //     -Pandroid.injected.signing.key.alias='$KEY_ALIAS' \
                    //     -Pandroid.injected.signing.key.password='$KEY_PASS'
                    // """
                }
            }
            post {
                success {
                    // stash name: 'build-artifacts', includes: '**/build/outputs/apk/**/*.apk, **/build/outputs/bundle/**/*.aab'
                    stash name: 'build-artifacts', includes: '**/README.md'
                }
            }
        }
        stage('Upload to S3') {
            agent {
                docker { image 'amazon/aws-cli' } 
            }
            steps {
                unstash 'build-artifacts'

                withCredentials([usernamePassword(credentialsId: 'aws-s3-credentials', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    script {
                        def apkPath = sh(script: 'find . -name "*.apk" | head -n 1', returnStdout: true).trim()
                        def aabPath = sh(script: 'find . -name "*.aab" | head -n 1', returnStdout: true).trim()
                        
                        def datePrefix = new Date().format("yyyyMMdd-HHmm")
                        
                        echo "Uploading APK: ${apkPath}"
                        sh "aws s3 cp \"${apkPath}\" s3://${S3_BUCKET}/${S3_PATH}/${datePrefix}-app-release.apk --region ${AWS_REGION}"

                        echo "Uploading AAB: ${aabPath}"
                        sh "aws s3 cp \"${aabPath}\" s3://${S3_BUCKET}/${S3_PATH}/${datePrefix}-app-release.aab --region ${AWS_REGION}"
                    }
                }
            }
        }
    }
}