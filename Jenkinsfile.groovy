node {
    properties([properties([parameters([string(defaultValue: '127.0.0.1', description: 'PLEASE GIVE ip TO GENERATE THIS SITE', name: 'IP', trim: false)])])])

    stage("install git"){
        sh "ssh ec2-user@${IP}   yum install git python-pip -y"
    }
    stage('clone a repo'){
        git "git@github.com:seedoffd/flask-examples.git"
    }
    stage('Copy files'){
       sh 'scp -r * ec2-user@${IP}:/tmp/'
    }
    stage('Install requirements'){
        sh "ssh ec2-user@${IP} pyhton /tmp/01-hello-world/hello.py"

    } 
    stage('Run app'){
        sh 'ssh ec2-user@${IP} pyhton /tmp/app.py'
    }

}

